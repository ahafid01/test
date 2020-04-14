import React from 'react';
import { BrowserRouter as Router } from 'react-router-dom';
import { DataProvider } from '../context/context';
import Routes from '../routes';
import 'primereact/resources/themes/nova-light/theme.css';
import 'primereact/resources/primereact.min.css';
import 'primeicons/primeicons.css';

const App = () => (
  <DataProvider>
    <Router children={Routes} basename={'/'} />
  </DataProvider>
);

export default App;
