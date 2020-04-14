import React from 'react';
import { Button, TextField, Card, Container, Input } from '@material-ui/core';

const Form = props => {
  const containerStyle = {
    padding: '20px',
    textAlign: 'center'
  };
  return (
    <Container maxWidth="sm" p={20}>
      <Card style={containerStyle}>
        <form onSubmit={props.handleSubmit}>
          <Input
            id="name"
            name="username"
            label="Nom d'utilisateur"
            fullWidth
            value={props.username}
            onChange={props.handleChange}
            placeholder="Nom d'utilisateur"
            data-testid="username"
          />
          <Input
            id="password"
            name="password"
            label="Mot de passe"
            fullWidth
            type="password"
            value={props.username}
            onChange={props.handleChange}
            placeholder="Mot de passe"
            data-testid="password"
          />
          <Button data-testid="valider" type="submit" variant="contained" color="primary" m={15}>
            Valider
          </Button>
        </form>
      </Card>
    </Container>
  );
};

export default Form;
