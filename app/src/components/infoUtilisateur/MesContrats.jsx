import React from 'react';
import Offre from './Offre.jsx';
import Buttons from './Buttons.jsx';

const MesContrats = () => {
  return (
    <>
      <form>
        <h1 className="formulaire-panel-title">
          <i className="pi pi-file-o"></i>
          <span>Mes contrats</span>
        </h1>
        <hr />
        <div className="wrapper-contracts">
          <div className="someheader p-grid contract-header">
            <div className="p-col-3">
              <span className="contract--header-text">Nom de l'offre</span>
            </div>
            <div className="p-col-6">
              <span className="contract--header-text">Options sousrcrites</span>
            </div>
            <div className="p-col-3">
              <span className="contract--header-text">tarif</span>
            </div>
          </div>
          <Offre />
          <Offre />
          <Offre />
          <Offre />
        </div>
        <Buttons />
      </form>
    </>
  );
};

export default MesContrats;
