import React, { useState } from 'react';
import { InputText } from 'primereact/inputtext';
import { InputMask } from 'primereact/inputmask';
import Buttons from './Buttons.jsx';

const MesInformationsPersonnelles = props => {
  // props.telephonePrincipal = '0123456789';
  const [identifiant, setUserIndentifiant] = useState(props.email || '');
  const [nom, setUserNom] = useState(props.nom || '');
  const [prenom, setUserPrenom] = useState(props.prenom || '');
  const [naissance, setUserNaissance] = useState(props.dateNaissance || '');
  let maskedTelephone = '';
  if (props.telephonePrincipal) {
    maskedTelephone = props.telephonePrincipal
      .split('')
      .slice(1)
      .join('');
  }
  const [telephoneMask, setUserTelephoneMask] = useState(maskedTelephone || '');
  const [telephone, setUserTelephone] = useState(props.telephonePrincipal || '');

  let maskedTelephoneSecond = '';
  if (props.telephoneSecondaire) {
    maskedTelephoneSecond = props.telephoneSecondaire
      .split('')
      .slice(1)
      .join('');
  }
  const [telephoneSecondMask, setUserTelephoneSecondMask] = useState(maskedTelephoneSecond || '');
  const [telephoneSecond, setUserTelephoneSecond] = useState(props.telephoneSecondaire || '');

  return (
    <>
      <form>
        <h1 className="formulaire-panel-title">
          <i className="pi pi-id-card"></i>
          <span>Mes informations personnelles</span>
        </h1>
        <hr />
        <div className="content-section" id="mes-informations-section">
          <div className="p-col-12 p-md-10 input-user-info">
            <span className="p-float-label">
              <InputText
                data-testid="info-input-identifiant"
                style={{ width: '100%' }}
                id="in"
                value={identifiant}
                onChange={e => setUserIndentifiant(e.target.value)}
              />
              <label htmlFor="in">Identifiant</label>
            </span>
          </div>
          <div className="p-col-12 p-md-10 input-user-info">
            <span className="p-float-label">
              <InputText
                data-testid="info-input-user-nom"
                style={{ width: '100%' }}
                id="in"
                value={nom}
                onChange={e => setUserNom(e.target.value)}
              />
              <label htmlFor="in">Nom</label>
            </span>
          </div>
          <div className="p-col-12 p-md-10 input-user-info">
            <span className="p-float-label">
              <InputText
                data-testid="info-input-user-prenom"
                style={{ width: '100%' }}
                id="in"
                value={prenom}
                onChange={e => setUserPrenom(e.target.value)}
              />
              <label htmlFor="in">Prénom</label>
            </span>
          </div>
          <div className="p-col-12 p-md-10 input-user-info">
            <span className="p-float-label">
              <InputMask
                data-testid="info-input-naissance"
                mask="99/99/9999"
                style={{ width: '100%' }}
                id="info-input-naissance"
                value={naissance}
                onChange={e => setUserNaissance(e.target.value)}
              />
              <label htmlFor="in">Date de naissance</label>
            </span>
          </div>
          <div className="p-col-12 p-md-10 input-user-info">
            <span className="p-float-label">
              <InputMask
                data-testid="info-input-telephone"
                mask={'(+33) 9 99 99 99 99'}
                style={{ width: '100%' }}
                id="info-input-telephone"
                value={telephoneMask}
                onChange={e => {
                  setUserTelephoneMask(e.target.value);
                  // remove +33
                  const newTelephone = e.target.value
                    .split(' ')
                    .slice(1)
                    .join('');
                  setUserTelephone(`0${newTelephone}`);
                }}
              />
              <label htmlFor="in">Téléphone principal</label>
            </span>
          </div>

          <div className="p-col-12 p-md-10 input-user-info">
            <span className="p-float-label">
              <InputMask
                data-testid="info-input-telephone-second"
                mask={'(+33) 9 99 99 99 99'}
                style={{ width: '100%' }}
                id="info-input-telephone-second"
                value={telephoneSecondMask}
                onChange={e => {
                  setUserTelephoneSecondMask(e.target.value);
                  // remove (+33)
                  const newTelephoneSecond = e.target.value
                    .split(' ')
                    .slice(1)
                    .join('');
                  setUserTelephoneSecond(`0${newTelephoneSecond}`);
                }}
              />
              <label htmlFor="in">Téléphone secondaire (facultatif)</label>
            </span>
          </div>
        </div>

        <Buttons />
      </form>
    </>
  );
};

export default MesInformationsPersonnelles;
