import React, { Component } from 'react';

import { DataTable } from 'primereact/datatable';
import { Column } from 'primereact/column';
import { Message } from 'primereact/message';

import AnnuaireService from './AnnuaireService';
import Spinner from '../spinner';

class Annuaire extends Component {
  state = {
    professionnelsAnnuaire: [],
    totalRecords: 0,
    rows: 10,
    page: 0,
    size: 10,
    loading: false,
    initialTableLoading: false,
    first: 0,
    errors: []
  };

  annuaireService = new AnnuaireService();

  async componentDidMount() {
    this.setState({
      initialTableLoading: true
    });

    await this.annuaireService
      .getDataByPageAndSize(this.state.page, this.state.rows)
      .then(data => {
        const { content, totalElements } = data;
        this.setState({
          professionnelsAnnuaire: content,
          totalRecords: totalElements,
          initialTableLoading: false
        });
      })
      .catch(err => {
        this.setState(() => ({
          errors: [
            {
              message: 'Erreur lors de la récupération des données',
              apiMessage: err.message
            }
          ],
          initialTableLoading: false
        }));
      });
  }

  handleOnPage = event => {
    this.setState({ loading: true, rows: event.rows });
    this.annuaireService.getDataByPageAndSize(event.page, event.rows).then(data => {
      const { content } = data;
      this.setState({
        professionnelsAnnuaire: content,
        loading: false,
        first: event.page * event.rows,
        page: event.page
      });
    });
  };

  renderErrors() {
    const errors = this.state.errors || [];
    return errors.length > 0 ? (
      <div data-testid="annuaire-error-message">
        {errors.map((error, i) => (
          <div key={i} className="p-col-12 p-md-12">
            <Message severity="error" text={error.message} />
          </div>
        ))}
      </div>
    ) : null;
  }

  render() {
    if (this.state.initialTableLoading) {
      return <Spinner />;
    }

    return (
      <>
        <h1>Annuaire</h1>
        <div>
          <div className="content-section introduction">
            <div className="feature-intro">
              <p>Liste des professionnels de santé enregistrés</p>
            </div>
          </div>
          {this.state.errors && this.state.errors.length > 0 ? (
            this.renderErrors()
          ) : (
            <div className="content-section implementation" id="annuaire-wrapper">
              <DataTable
                responsive={true}
                value={this.state.professionnelsAnnuaire}
                lazy={true}
                onPage={this.handleOnPage}
                paginator={true}
                rows={this.state.rows}
                rowsPerPageOptions={[10, 20, 50]}
                totalRecords={this.state.totalRecords}
                loading={this.state.loading}
                first={this.state.first}
                autoLayout
              >
                <Column field="identifiantPP" header="Identifiant PP" />
                <Column field="codeCivilite" header="Code civilité" />
                <Column field="nomExercice" header="Nom d'exercice" />
                <Column field="prenomExercice" header="Prénom" />
                <Column field="libelleProfession" header="Profession" />
                <Column field="libelleModeExercice" header="Mode exercice" />
                <Column field="numeroSiretSite" header="SIRET" />
                <Column field="raisonSocialeSite" header="Raison Sociale" />
                <Column
                  field="complementDestinataireCoordStructure"
                  header="Complément destinataire"
                />
                <Column field="numeroVoieCoordStructure" header="Numéro de voie" />
                <Column field="libelleTypeVoieCoordStructure" header="Type de voie" />
                <Column field="libelleVoieCoordStructure" header="Voie" />
                <Column field="mentionDistributionCoordStructure" header="Mention distribution" />
                <Column field="bureauCedexCoordStructure" header="Cedex" />
                <Column field="codePostalCoordStructure" header="Code postal" />
                <Column field="libelleCommuneCoordStructure" header="Commune" />
                <Column field="telephoneCoordStructure" header="Téléphone" />
                <Column field="adresseEmailCoordStructure" header="Adresse email" />
              </DataTable>
            </div>
          )}
        </div>
      </>
    );
  }
}

export default Annuaire;
