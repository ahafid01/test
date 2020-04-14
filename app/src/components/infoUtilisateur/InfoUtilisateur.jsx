import React, { useState, useEffect } from 'react';
import { useLocation } from 'react-router-dom';
import { Message } from 'primereact/message';
import UtilisateurService from './UtilisateurService';
import FormulaireInfo from './FormulaireInfo.jsx';
import Spinner from '../spinner';

const InfoUtilisateur = () => {
  const utilisateurService = new UtilisateurService();

  const useQuery = () => new URLSearchParams(useLocation().search);
  const queryParams = useQuery();
  const userEmail = queryParams.get('email');
  const [userInfo, setUserInfo] = useState();
  const [error, setError] = useState('');
  const [errorMessage, setErrorMessage] = useState('');
  const [errorGlobal, setErrorGlobal] = useState();
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    const fetchUserInfo = async email => {
      setLoading(true);
      const userInfoResponse = await utilisateurService.getInfoByMail(email);

      if (userInfoResponse.status >= 500) {
        // server exception or no response from server
        setErrorGlobal(userInfoResponse);
      }

      if (userInfoResponse.data) {
        // server gives error message
        setErrorMessage(userInfoResponse.data.message);
        return setError(userInfoResponse);
      }

      const dateNaissance = new Date(userInfoResponse.dateNaissance).toLocaleDateString('fr-FR', {
        day: 'numeric',
        month: 'numeric',
        year: 'numeric'
      });

      const mappedResponse = {
        ...userInfoResponse,
        dateNaissance
      };

      setLoading(false);

      return setUserInfo(mappedResponse);
    };

    fetchUserInfo(userEmail);
  }, []);

  if (errorGlobal) {
    throw new Error(errorGlobal);
  }

  if (errorMessage || error) {
    return (
      <div id="formulaire-info">
        <div className="p-justify-center" data-testid="formulaire-info-user-error-message">
          <Message
            data-testid="formulaire-info-user-error-message"
            severity="error"
            text={errorMessage}
          ></Message>
        </div>
      </div>
    );
  }

  if (loading) {
    return <Spinner />;
  }

  return (
    <div id="formulaire-info" data-testid="formulaire-info-test">
      <div className="p-justify-center">
        <h1 className="informations-welcome--big">Bonjour {userInfo && userInfo.prenom},</h1>
        <div>
          <p className="informations-welcome--text">
            <span className="informations-welcome--strong">
              Bienvenue sur votre espace client !
            </span>
            <br></br> Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor
            incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud
            exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.
          </p>
        </div>
      </div>
      {userInfo && <FormulaireInfo userInfo={userInfo} />}
    </div>
  );
};

export default InfoUtilisateur;
