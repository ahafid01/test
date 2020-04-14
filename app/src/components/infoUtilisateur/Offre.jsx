import React from 'react';

const Offre = () => {
  return (
    <div className="single-contract-wrapper p-grid contract-item">
      <div className="p-col-3">
        <div className="p-grid">
          <div className="p-col-12">
            <span className="contract-name">Stellair intégral</span>
          </div>
        </div>
      </div>
      <div className="p-col-6">
        <div className="p-grid">
          <div className="p-col-12 option-single-wrapper">
            <i className="pi pi-check-circle"></i>
            <span className="option-single">Offre 1</span>
          </div>
          <div className="p-col-12 option-single-wrapper">
            <i className="pi pi-check-circle"></i>
            <span className="option-single">Offre 2</span>
          </div>
          <div className="p-col-12 option-single-wrapper">
            <i className="pi pi-check-circle"></i>
            <span className="option-single">Offre 3</span>
          </div>
        </div>
      </div>
      <div className="p-col-3">
        <div className="p-grid">
          <div className="p-col-12 option-single-wrapper">
            <span className="option-single">Offre 1 € prix</span>
          </div>
          <div className="p-col-12 option-single-wrapper">
            <span className="option-single">Offre 2 € prix</span>
          </div>
          <div className="p-col-12 option-single-wrapper">
            <span className="option-single">Offre 3 € prix</span>
          </div>
        </div>
      </div>
      <div className="p-col-3"></div>
      <div className="p-col-9 divider-total">
        <hr className="divider-total-hr" />
      </div>
      <div className="p-col-3"></div>
      <div className="p-col-6">
        <span className="total">TOTAL</span>
      </div>
      <div className="p-col-3">
        <span className="total">54.99 € TTC</span>
      </div>
    </div>
  );
};

export default Offre;
