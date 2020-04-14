import React from 'react';
import Enzyme, { shallow, render as renderEnzyme } from 'enzyme';
import Adapter from 'enzyme-adapter-react-16';
import { render, waitForElement } from '@testing-library/react';
import { Router as MemoryRouter } from 'react-router-dom';

import App, {
  InfoUtilisateur as LazyInfoUtilisateur,
  infoUtilisateurRender,
  QualificationTable as LazyQualificationTable,
  qualificationTableRender,
  Annuaire as LazyAnnuaire,
  annuaireRender,
  Dashboard as LazyDashboard,
  dashboardRender
} from './App';

Enzyme.configure({
  adapter: new Adapter()
});
it('renders without crashing', () => {
  const component = shallow(<App />);

  expect(component).not.toBe(null);
});

it('Should render function renderer', () => {
  const renderInfoUtilisateurRenderer = () => renderEnzyme(infoUtilisateurRender);
  const renderQualificationTableRenderer = () => renderEnzyme(qualificationTableRender);
  const renderAnnuaireRenderer = () => renderEnzyme(annuaireRender);
  const renderDashboardRenderer = () => renderEnzyme(dashboardRender);
  const wrapper = renderInfoUtilisateurRenderer();
  expect(wrapper.find('#formulaire-info'));
  expect(renderQualificationTableRenderer().find('qualification-table')).not.toBe(null);
  expect(renderAnnuaireRenderer().find('#annuaire-wrapper')).not.toBe(null);
  expect(renderDashboardRenderer().find('#dashboard')).not.toBe(null);
});

it('Shoudl renders spinner on mount', () => {
  const historyMock = { push: jest.fn(), location: {}, listen: jest.fn(), createHref: jest.fn() };

  const { getByText } = render(
    <React.Suspense fallback="test-spinner-fallback">
      <MemoryRouter history={historyMock}>
        <LazyInfoUtilisateur />
        <LazyQualificationTable />
        <LazyAnnuaire />
        <LazyDashboard />
      </MemoryRouter>
    </React.Suspense>
  );
  expect(getByText('test-spinner-fallback')).not.toBe(null);
});

it('Shoudld render lazy component after next tick', async () => {
  const historyMock = { push: jest.fn(), location: {}, listen: jest.fn(), createHref: jest.fn() };

  const { getByTestId } = render(
    <React.Suspense fallback="test-spinner-fallback">
      <MemoryRouter history={historyMock}>
        <App />
      </MemoryRouter>
    </React.Suspense>
  );

  const lazyElement = await waitForElement(() => getByTestId('layout-main-test'));

  expect(lazyElement).toBeInTheDocument();
});
