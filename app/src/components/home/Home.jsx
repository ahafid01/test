import React from 'react';
import { Link } from 'react-router-dom';
import { InputText } from 'primereact/inputtext';

const Home = () => {
  return (
    <div>
      <Link to="/login">Consulter la base de donnée qualification</Link>
      <InputText value="toto" />
    </div>
  );
};

export default Home;
