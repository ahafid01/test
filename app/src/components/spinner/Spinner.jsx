import React from 'react';
// Prime React related
import { ProgressSpinner } from 'primereact/progressspinner';
import './Spinner.scss';

const WaitSpinner = () => {
  return (
    <div className="spinner-container" data-testid="spinner">
      <ProgressSpinner className="flex-center" />
    </div>
  );
};

export default WaitSpinner;
