import React, { useEffect, useState } from 'react';
import './App.css';
import Navbar from './components/Navbar';
import EventList from './components/EventList';
import { mockHarvardEvents } from './data/Event';

function App() {
  const [backendStatus, setBackendStatus] = useState('Checking...');
  const backendUrl = process.env.REACT_APP_API_ENDPOINT; // The EC2 instance public DNS or IP

  useEffect(() => {
    const pingBackend = async () => {
      console.log(`Attempting to ping backend at: ${backendUrl}`);

      try {
        const response = await fetch(backendUrl, { method: 'HEAD' });
        console.log('Response from backend:', response);

        if (response.ok) {
          setBackendStatus('EC2 instance is reachable!');
          console.log('Backend is reachable and returned OK status.');
        } else {
          setBackendStatus(`Instance reachable but returned status: ${response.status}`);
          console.error(`Backend reachable but returned status code: ${response.status}`);
        }
      } catch (error) {
        setBackendStatus('Failed to connect to EC2 instance.');
        console.error('Error while trying to connect to backend:', error);
      }
    };

    pingBackend();
  }, [backendUrl]);

  return (
    <div className="App">
      <Navbar />
      <EventList events={mockHarvardEvents} />
    </div>
  );
}

export default App;
