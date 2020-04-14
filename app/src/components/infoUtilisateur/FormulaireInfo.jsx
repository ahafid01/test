import React, { useState } from 'react';
import { TabView, TabPanel } from 'primereact/tabview';
import './InfoUtilisateur.scss';
import { useLocation, useHistory } from 'react-router-dom';

import MesContrats from './MesContrats.jsx';
import MesInformations from './MesInformations.jsx';
import MesAdresses from './MesAdresses.jsx';

const FormulaireInfo = ({ userInfo }) => {
  const useQuery = () => new URLSearchParams(useLocation().search);
  const queryParams = useQuery();

  const userEmail = queryParams.get('email');
  const infopanel = Number(queryParams.get('infopanel')) || 0;

  const history = useHistory();

  const [activeIndex, setActiveIndex] = useState(infopanel);

  return (
    <div className="formulaire-container" data-testid="formulaire-container">
      <TabView
        activeIndex={activeIndex}
        onTabChange={e => {
          history.replace({ search: `?email=${userEmail}&infopanel=${e.index}` });
          setActiveIndex(e.index);
        }}
      >
        <TabPanel data-testid="tab-infos-personnelles" header="Mes informations personnelles">
          <MesInformations {...userInfo} />
        </TabPanel>
        <TabPanel header="Mes adresses">
          <MesAdresses userInfo={userInfo} {...userInfo} />
        </TabPanel>
        <TabPanel header="Mes contrats">
          <MesContrats {...userInfo} />
        </TabPanel>
      </TabView>
    </div>
  );
};
export default React.memo(FormulaireInfo);
