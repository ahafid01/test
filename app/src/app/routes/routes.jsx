import React from 'react';
import { Switch, Route } from 'react-router-dom';
import Home from '../../components/home';
import Login from '../../components/login';
import Dashboard from '../../components/dashboard';

const Routes = (
  <Switch>
    <Route exact={true} path="/" component={Home} />
    <Route exact={true} path="/login" component={Login} />
    <Route exact={true} path="/dashboard" component={Dashboard} />
  </Switch>
);

export default Routes;
