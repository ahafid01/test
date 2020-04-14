import React, { Suspense, lazy } from 'react';
import { Route, Switch } from 'react-router-dom';
import AppTopbar from './components/navbar/AppTopbar';
import 'primereact/resources/themes/nova-light/theme.css';
import 'primereact/resources/primereact.min.css';
import 'primeicons/primeicons.css';
import 'primeflex/primeflex.css';
import '@fullcalendar/core/main.css';
import '@fullcalendar/daygrid/main.css';
import '@fullcalendar/timegrid/main.css';
import './layout/layout.scss';
import './App.scss';
import Login from './components/login';
import Spinner from './components/spinner';
import ErrorBoundary from './components/global/errorBoundary';

export const InfoUtilisateur = lazy(() => import('./components/infoUtilisateur'));
export const QualificationTable = lazy(() => import('./components/datatable'));
export const Annuaire = lazy(() => import('./components/annuaire'));
export const Dashboard = lazy(() => import('./components/dashboard/Dashboard.jsx'));

export const infoUtilisateurRender = props => <InfoUtilisateur {...props} />;
export const qualificationTableRender = props => <QualificationTable {...props} />;
export const annuaireRender = props => <Annuaire {...props} />;
export const dashboardRender = props => <Dashboard {...props} />;

const Routes = () => (
  <ErrorBoundary>
    <Switch>
      <Route path="/" exact render={dashboardRender} />
      <Route exact path="/qualification" render={qualificationTableRender} />
      <Route exact={true} path="/login" component={Login} />
      <Route exact={true} path="/annuaire" render={annuaireRender} />
      <Route exact={true} path="/informations" render={infoUtilisateurRender} />
    </Switch>
  </ErrorBoundary>
);

const App = () => {
  return (
    <Suspense fallback={<Spinner />}>
      <div className="layout-wrapper layout-static layout-static-sidebar-inactive">
        <AppTopbar />

        <div className="layout-main" data-testid="layout-main-test">
          <Routes />
        </div>
      </div>
    </Suspense>
  );
};

export default App;
