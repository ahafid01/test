import React, { useState } from 'react';
import { InputSwitch } from 'primereact/inputswitch';
import Adresse from './Adresse.jsx';
import Buttons from './Buttons.jsx';

const MesAdresses = ({ userInfo }) => {
  const { adresseLivraison, adresseFacturation } = userInfo || {};
  const [adresseIdentique, setAdresseIdentique] = useState(adresseLivraison === adresseFacturation);

  return (
    <>
      <form>
        <h1 className="formulaire-panel-title">
          <i className="pi pi-map-marker"></i>
          <span>Mes adresses</span>
        </h1>
        <hr />
        <div className="content-section">
          <h3 className="h3-adresses">
            <span className="title-section-adresses">Mon adresse de facturation</span>
          </h3>
          {userInfo && (
            <Adresse
              adresse={adresseFacturation}
              customLabel={{ labelNomComplet: 'Nom adresse de facturation' }}
            />
          )}
          <h3 className="h3-adresses h3-adresse-identique">
            <span className="title-section-adresses">Mon adresse de livraison</span>
          </h3>
          <div className="p-cold-5 adresse-switch-wrapper">
            <InputSwitch
              className="switch-identique"
              checked={adresseIdentique}
              onChange={e => setAdresseIdentique(e.value)}
            />
            <span className="text-identique">Identique à mon adresse de livraison</span>
          </div>
          {!adresseIdentique && (
            <div className="adresse-identique">
              {userInfo && (
                <Adresse
                  adresse={adresseLivraison}
                  customLabel={{ labelNomComplet: 'Nom adresse de livraison' }}
                />
              )}
            </div>
          )}
        </div>

        <Buttons />
      </form>
    </>
  );
};

export default MesAdresses;
