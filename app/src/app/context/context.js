import React, { useState, createContext, useContext, useEffect } from 'react';
import moment from 'moment';
import { PROSPECTS_PATH } from '../api/constants';

const axios = require('axios');

const DataContext = createContext({ data: [] });

export const useDataContext = () => useContext(DataContext);

const DataProvider = props => {
  const [data, setData] = useState([]);
  const [loading, setLoading] = useState(true);
  const [credentials, setCredentials] = useState({ username: '', password: '' });
  const [errors, setErrors] = useState({});

  const fetchQualificatedUsersWithCreds = ({ username, password }) => {
    async function fetchData() {
      const response = await axios.post(PROSPECTS_PATH, { username, password });

      const dataResponse = await response.data;

      setData(dataResponse);

      setLoading(false);
    }

    try {
      fetchData();
    } catch (e) {
      setErrors(e);
    }
  };

  useEffect(() => {
    async function fetchData() {
      const response = await axios.get(PROSPECTS_PATH, {
        headers: {
          'Access-Control-Allow-Origin': '*'
        },
        proxy: {
          host: '127.0.0.1',
          port: 8080
        }
      });

      let dataResponse = await response.data;

      dataResponse = dataResponse.map(professionnel => {
        let dateQualif;
        if (professionnel.dateQualif) {
          dateQualif = moment(professionnel.dateQualif).format('DD/MM/YYYY HH:mm');
        }
        return {
          ...professionnel,
          dateQualif
        };
      });

      setData(dataResponse);

      setLoading(false);
    }

    try {
      fetchData();
    } catch (e) {
      setErrors(e);
    }
  }, []);

  return (
    <DataContext.Provider
      value={{
        loading,
        data,
        credentials,
        setCredentials,
        setData,
        fetchQualificatedUsersWithCreds,
        errors
      }}
    >
      {props.children}
    </DataContext.Provider>
  );
};

const DataConsumer = DataContext.Consumer;
export { DataProvider, DataConsumer, DataContext };
