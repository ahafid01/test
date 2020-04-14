import React, { useContext } from 'react';
import { Formik } from 'formik';
import { useHistory } from 'react-router-dom';
import LoginFormTemplate from './LoginFormTemplate.jsx';

import { DataContext } from '../../app/context/context';

const LoginForm = () => {
  const context = useContext(DataContext);
  const history = useHistory();

  const handleSubmit = async values => {
    await context.fetchQualificatedUsersWithCreds(values);

    await history.push('/qualification');
  };

  const intialValues = {
    username: '',
    password: ''
  };

  return (
    <div>
      <Formik
        data-testid="formik-wrapper-test"
        initialValues={intialValues}
        onSubmit={handleSubmit}
      >
        {props => <LoginFormTemplate {...props} />}
      </Formik>
    </div>
  );
};

export default LoginForm;
