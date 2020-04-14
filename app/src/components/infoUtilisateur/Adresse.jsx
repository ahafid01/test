import React, { useState } from 'react';
import { InputText } from 'primereact/inputtext';

const Adresse = ({ adresse, customLabel = {} }) => {
  const { nomComplet = '', codePostal = '', ligne1 = '', ligne2 = '', ville = '' } = adresse || {};
  const [nomAdresse, setNomAdresse] = useState(nomComplet);
  const [postalCode, setPostalCode] = useState(codePostal);
  const [ligneAdresse, setLigneAdresse] = useState(ligne1);
  const [ligne2Adresse, setLigne2Adresse] = useState(ligne2);
  const [villeAdresse, setVilleAdresse] = useState(ville);

  return (
    <>
      <div className="p-col-12 p-md-10 input-user-info">
        <span className="p-float-label">
          <InputText
            data-testid="info-input-nom-adresse"
            style={{ width: '100%' }}
            id="in"
            value={nomAdresse}
            onChange={e => setNomAdresse(e.target.value)}
          />
          <label htmlFor="in">{customLabel.labelNomComplet || 'Nom complet'}</label>
        </span>
      </div>
      <div className="p-col-12 p-md-10 input-user-info">
        <span className="p-float-label">
          <InputText
            data-testid="info-input-ligne-adresse"
            style={{ width: '100%' }}
            id="in"
            value={ligneAdresse}
            onChange={e => setLigneAdresse(e.target.value)}
          />
          <label htmlFor="in">Adresse</label>
        </span>
      </div>
      <div className="p-col-12 p-md-10 input-user-info">
        <span className="p-float-label">
          <InputText
            data-testid="info-input-ligne2"
            style={{ width: '100%' }}
            id="in"
            value={ligne2Adresse}
            onChange={e => setLigne2Adresse(e.target.value)}
          />
          <label htmlFor="in">Complément d'adresse (facultatif)</label>
        </span>
      </div>
      <div className="p-grid p-col-12">
        <div className="p-col-5 p-md-3 input-user-info">
          <span className="p-float-label">
            <InputText
              data-testid="info-input-postalcode"
              style={{ width: '100%' }}
              id="in"
              // Temporary wating stellair to fix the value to String
              keyfilter="num"
              value={postalCode || ''}
              onChange={e => setPostalCode(e.target.value)}
            />
            <label htmlFor="in">Code postal</label>
          </span>
        </div>
        <div className="p-col-5 p-md-7 input-user-info user-info-city">
          <span className="p-float-label">
            <InputText
              data-testid="info-input-ville"
              style={{ width: '100%' }}
              id="in"
              value={villeAdresse}
              onChange={e => setVilleAdresse(e.target.value)}
            />
            <label htmlFor="in">Ville</label>
          </span>
        </div>
      </div>
    </>
  );
};

export default Adresse;
