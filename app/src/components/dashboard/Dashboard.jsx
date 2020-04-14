import React from 'react';
import { Link } from 'react-router-dom';

const Dashboard = () => {
  return (
    <>
      <div className="p-grid p-fluid dashboard" id="dashboard">
        <div className="p-col-12 p-md-6 p-lg-4"></div>
        <div className="p-col-12 p-lg-6">
          <h1 style={{ fontSize: '16px' }}>PFD</h1>
          <Link to="/qualification">Qualification</Link>
          <br></br>
          <Link to="/annuaire">Annuaire</Link>
          <br></br>
          <Link to="/informations?email=patrice.martin@ingenico.com">User avec adresse</Link>
          <br />
          <Link to="/informations?email=raphaelle.mazure@ingenico.com">User sans adresse</Link>
          <br />
          <Link to="/informations?email=test-complet@olaqin.fr">User sans adresse</Link>
          <br />
          <Link to="/informations">Mes informations</Link>{' '}
        </div>
      </div>
    </>
  );
};

export default Dashboard;
