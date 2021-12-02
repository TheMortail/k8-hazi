import Axios from "axios";

const api = Axios.create({
    baseURL: '/api/',
});

const topic = "hazi";

const URL = window._env_.REACT_APP_BACKEND_URL || process.env.REACT_APP_BACKEND_URL;

const kafkaApi = {
    getMessages: (groupId) => {
        console.log('Calling get messages from API');
        return api.get(`${topic}/${groupId}`);
    },

    sendMessage: (name, description) => {
        let msg = {
            name: name,
            description: description
        }
        return api.post('http://'+URL+'/api/send', msg);
    }
}


export default kafkaApi;