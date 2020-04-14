import axios from 'axios';
import { ANNUAIRE_PROFESSIONNELS_PATH } from '../../app/api/constants';
/* eslint class-methods-use-this: ["error", { "exceptMethods": ["getDataByPageAndSize"] }] */

class AnnuaireService {
  async getDataByPageAndSize(page, size) {
    const response = await axios.get(`${ANNUAIRE_PROFESSIONNELS_PATH}?page=${page}&size=${size}`);
    const dataResponse = await response.data;
    return dataResponse;
  }
}

export default AnnuaireService;
