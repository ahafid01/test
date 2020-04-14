import React from 'react';
import { Button } from 'primereact/button';

const Buttons = () => {
  return (
    <div
      style={{
        float: 'right'
      }}
      className="button-formulaire-group"
    >
      <Button
        label="Réinitialiser"
        className="p-button-rounded p-button-secondary reset-formulaire"
        disabled
      />
      <Button className="save-formulaire" label="ENREGISTRER" disabled="disabled" />
    </div>
  );
};

export default Buttons;
