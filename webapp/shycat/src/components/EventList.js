import React from 'react';
import EventCard from './EventCard';

const EventList = ({ events }) => {
    return (
        <div className="event-list grid gap-6 p-6">
            {events.map((event, index) => (
                <EventCard key={index} event={event} />
            ))}
        </div>
    );
};

export default EventList;
