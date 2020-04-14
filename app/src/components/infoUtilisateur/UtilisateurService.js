/* eslint class-methods-use-this: ["error", { "exceptMethods": ["getInfoByMail"] }] */
import axios from 'axios';
import { UTILISATEURS_PATH } from '../../app/api/constants';

class UtilisateurService {
  async getInfoByMail(mail) {
    try {
      const response = await axios.get(`${UTILISATEURS_PATH}?email=${mail}`);
      const dataResponse = await response.data;
      return dataResponse;
    } catch (err) {
      return err.response;
    }
  }
}

export default UtilisateurService;
