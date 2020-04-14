import 'react-app-polyfill/ie11';
import React from 'react';
import ReactDOM from 'react-dom';
import { HashRouter } from 'react-router-dom';
import { DataProvider } from './app/context/context';
import App from './App';
// import * as serviceWorker from './serviceWorker';

ReactDOM.render(
  <DataProvider>
    <HashRouter>
      <App></App>
    </HashRouter>
  </DataProvider>,

  document.getElementById('root')
);

// If you want your app to work offline and load faster, you can change
// unregister() to register() below. Note this comes with some pitfalls.
// Learn more about service workers: http://bit.ly/CRA-PWA
// serviceWorker.unregister();
