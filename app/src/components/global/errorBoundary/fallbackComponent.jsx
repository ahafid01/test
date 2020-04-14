import React from 'react';

import { Message } from 'primereact/message';
import { Link } from 'react-router-dom';
import './fallbackComponent.scss';

const CustomErrorBoundaryFallbackComponent = props => {
  return (
    <div className="p-fluid">
      <div className="p-grid">
        <div className="p-col-12">
          <div
            className="p-messages p-component p-messages-error"
            style={{ margin: '0 0 1em 0', display: 'block' }}
          >
            <div className="p-messages-wrapper">
              <span className="p-messages-icon pi pi-fw pi-2x pi-check"></span>
              <ul>
                <li>
                  <span className="p-messages-detail">
                    Une erreur technique s'est produite, veuillez réessayer ultérieurement.
                  </span>
                </li>
                <li>
                  <span className="p-messages-detail">
                    Si le problème persiste veuillez contacter Olaqin.
                  </span>
                </li>
              </ul>
            </div>
          </div>
        </div>
      </div>
    </div>
  );
};

export default CustomErrorBoundaryFallbackComponent;
