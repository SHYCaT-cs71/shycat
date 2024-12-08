import React, { useEffect, useState } from 'react';
import './App.css';
import Navbar from './components/Navbar';
import EventList from './components/EventList';
import Event, { mockHarvardEvents } from './data/Event';

function App() {
  const [events, setEvents] = useState([]);
  const [backendStatus, setBackendStatus] = useState('Checking...');
  const backendUrl = process.env.REACT_APP_API_URL

  useEffect(() => {
    const fetchEvents = async () => {
      console.log(`Fetching events from: ${backendUrl}/events`);

      try {
        const response = await fetch(`${backendUrl}/events`);
        if (!response.ok) {
          throw new Error(`Failed to fetch events, status: ${response.status}`);
        }

        const eventData = await response.json();
        // Parse events into Event class instances
        const eventInstances = eventData.map(event => new Event(event));
        setEvents(eventInstances);

        setBackendStatus('Backend is reachable and events fetched successfully.');
        console.log(backendStatus);
      } catch (error) {
        setBackendStatus('Failed to connect to the backend or fetch events.');
        console.error('Error fetching events:', error);
        console.log(backendStatus);
      }
    };

    fetchEvents();
  }, [backendUrl]);

  // Log backendStatus whenever it changes
  useEffect(() => {
    console.log('Backend status updated:', backendStatus);
  }, [backendStatus]);


  return (
    <div className="App">
      <Navbar />

      {/* Display section for fetched backend events */}
      <section>
        <h2>Events from Backend</h2>
        {events.length > 0 ? (
          <EventList events={events} />
        ) : (
          <p>No events fetched from the backend yet.</p>
        )}
      </section>

      {/* TEMP: Display section for mock events */}
      <section>
        <h2>Mock Events</h2>
        <EventList events={mockHarvardEvents} />
      </section>
    </div>
  );
}

export default App;
