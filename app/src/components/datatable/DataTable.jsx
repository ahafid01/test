import React from 'react';
import { ReactTabulator } from 'react-tabulator';
import 'react-tabulator/lib/styles.css';
import 'react-tabulator/lib/css/tabulator.min.css';
import './datatable.scss';
import { useDataContext } from '../../app/context/context';

const columns = [
  {
    title: 'Date de la qualification',
    field: 'dateQualif',
    width: 210,
    headerFilter: 'input'
  },
  { title: 'Civilité', field: 'civilite', width: 100 },
  { title: 'Nom', field: 'nom', width: 150, headerFilter: 'input' },
  { title: 'Prénom', field: 'prenom', width: 150, headerFilter: 'input' },
  { title: 'Code postal', field: 'codePostal', width: 130, headerFilter: 'input' },
  { title: 'Téléphone', field: 'telephone', width: 120 },
  { title: 'Email', field: 'email', width: 150 },
  { title: 'Profession', field: 'profession', width: 150 },
  { title: 'Réponse autre profession', field: 'reponseAutreProfession', width: 220 },
  { title: 'À recontacter', field: 'accepteEtreRecontacte', width: 150 },
  { title: 'Résultat', field: 'resultatQualif', width: 100 },
  { title: 'Raison de la qualification', field: 'raisonQualif', width: 220 }
];

const QUALIFICATION_ENUM = {
  MEDECIN_GENERALISTE: 'Médecin généraliste',
  AUTRE: 'Autre',
  MEDECIN_SPECIALISTE: 'Médecin spécialiste',
  NOK: 'Non ok',
  OK: 'Ok',
  LOCALISATION_DIFFERENTE: 'Localisation différente',
  LOCALISATION_LEGEREMENT_DIFFERENTE: 'Localisation légérement différente',
  NON_TROUVE: 'Non trouvé',
  UTILISATEUR_TROUVE: 'Utilisateur trouvé'
};

const DataTable = () => {
  const options = {
    movable: true,
    layout: 'fitColumns',
    maxWidth: 'fit-content'
  };
  const context = useDataContext();

  if (context.data.length === 0) {
    return <h1>Rien à afficher</h1>;
  }

  const data = context.data.map(professionnel => {
    const accepteEtreRecontacte = professionnel.accepteEtreRecontacte ? 'Oui' : 'Non';
    const profession = QUALIFICATION_ENUM[professionnel.profession];
    const raisonQualif = QUALIFICATION_ENUM[professionnel.raisonQualif];
    const resultatQualif = QUALIFICATION_ENUM[professionnel.resultatQualif];

    return {
      ...professionnel,
      accepteEtreRecontacte,
      profession,
      raisonQualif,
      resultatQualif
    };
  });

  return (
    <ReactTabulator
      data-test="test-tabulator"
      columns={columns}
      data={data}
      options={options}
      initialSort={[{ column: 'dateQualif', dir: 'dsc' }]}
      sorter="date"
      id="qualification-table"
    />
  );
};

export default DataTable;
