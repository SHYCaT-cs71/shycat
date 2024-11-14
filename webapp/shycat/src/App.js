import React from 'react';
import './App.css';
import Navbar from './components/Navbar';
import EventList from './components/EventList';
import { mockHarvardEvents } from'./data/EventData';

function App() {
  return (
    <div className="App">
      <Navbar />
      <EventList events={mockHarvardEvents} />
    </div>
  );
}

export default App;
