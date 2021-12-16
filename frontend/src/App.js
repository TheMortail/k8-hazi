import React, { useState } from 'react';
import SockJsClient from 'react-stomp';
import './App.css';
import Input from './components/Input/Input';
import Ads from './components/Ads/Ads';
import kafkaApi from './services/kafkaApi';
import AdList from "./components/List/AdList";

const SOCKET_URL = window._env_.REACT_APP_BACKEND_URL || process.env.REACT_APP_BACKEND_URL;

const App = () => {
  const [ads, setAds] = useState([])

  let onConnected = () => {
    console.log("Connected!!")
  }

  let onMessageReceived = (msg) => {
    console.log('New Message Received!!', msg);
    setAds(ads.concat(msg));
  }

  let onSendMessage = (name, description) => {
    kafkaApi.sendMessage(name, description).then(res => {
      console.log('Sent', res);
      window.location.reload(false);
    }).catch(err => {
      console.log('Error Occured while sending message to api');
    })
  }

  return (
      <div className="App">
          <SockJsClient
              url={'http://'+SOCKET_URL+'/ws-ad/'}
              topics={['/topic/group']}
              onConnect={onConnected}
              onDisconnect={console.log("Disconnected!")}
              onMessage={msg => onMessageReceived(msg)}
              debug={false}
          />
          <Ads
              ads={ads}
          />
          <Input onSendMessage={onSendMessage} />

          <AdList/>
      </div>
  )
}

export default App;
