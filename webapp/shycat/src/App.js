import React, { useEffect, useState } from 'react';
import { BrowserRouter, Route, Routes } from 'react-router-dom';
import './App.css';
import Navbar from './components/Navbar.js';
import EventList from './components/EventList.js';
import Event from './data/Event.js';
import EventDetail from './components/EventDetail.js';


function App() {
  const [events, setEvents] = useState([]);
  const [backendStatus, setBackendStatus] = useState('Checking...');
  const backendUrl = process.env.REACT_APP_API_URL;

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
        console.log(`Loaded ${eventInstances.length} events`);
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
  }, [backendUrl, backendStatus]);

  // Log backendStatus whenever it changes
  useEffect(() => {
    console.log('Backend status updated:', backendStatus);
  }, [backendStatus]);


  return (
    <BrowserRouter>
      <Navbar />
      <Routes>
        <Route path="/" element={
          <div className="App">
            {/* Display section for fetched backend events */}
            <section>
              <h2>Upcoming Events</h2>
              {events.length > 0 ? (
                <EventList events={events} />
              ) : (
                <p>No events fetched from the backend yet.</p>
              )}
            </section>
          </div>
        } />
        <Route path="/events/:eventId" element={<EventDetail />} />
      </Routes>
    </BrowserRouter>
  );
}

export default App;
