import React from 'react';
import ErrorBoundary from 'react-error-boundary';
import CustomErrorBoundaryFallbackComponent from './fallbackComponent.jsx';

export const CustomErrorBoundary = ({ children }) => {
  return (
    <ErrorBoundary FallbackComponent={CustomErrorBoundaryFallbackComponent}>
      {children}
    </ErrorBoundary>
  );
};

export default CustomErrorBoundary;
