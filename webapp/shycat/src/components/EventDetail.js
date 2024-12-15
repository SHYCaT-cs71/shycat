import React, { useEffect, useState } from "react";
import { useParams } from "react-router-dom";
import Event from ".././data/Event.js";
import { formatDate } from "../data/Utilities.js";


function EventDetail() {

    const backendUrl = process.env.REACT_APP_API_URL;

    const [event, setEvent] = useState(new Event({}));
    const { eventId } = useParams();
    console.log("eventId:", eventId);

    useEffect(() => {
        const getEvent = async () => {

            console.log(`Fetching event ${eventId} from: ${backendUrl}/events/${eventId}`);

            try {
                const response = await fetch(`${backendUrl}/events/${eventId}`);
                if (!response.ok) {
                    throw new Error(`Failed to fetch event, status: ${response.status}`);
                }

                const eventData = await response.json();
                console.log("eventData:", eventData);
                // Parse event into Event class instances
                let event = new Event(eventData);
                setEvent(event);
            } catch (error) {
                console.error('Error fetching event:', error);
                // console.log(backendStatus);
            }
        };

        getEvent();
    }, [eventId]);


    const startDateFormatted = event.startDate ? formatDate(event.startDate) : "No start date";
    const endDateFormatted = event.endDate ? formatDate(event.endDate) : null;

    let locationNameP;
    if (event.locationName) {
        locationNameP = <p className="event-location">Location: {event.locationName}</p>;
    }

    let locationAddressP;
    if (event.locationAddress) {
        locationAddressP = <p className="event-location">Address: {event.locationAddress}</p>;
    }

    let locationUrlP;
    if (event.locationUrl) {
        locationUrlP = <a href={event.locationUrl} target="_blank" rel="noopener noreferrer" 
                          className="event-location">Event Link: {event.locationUrl}</a>;
    }

    return (
        <div>
            <div className="hero bg-slate-100 min-h-screen">
                <div className="hero-content flex-col lg:flex-row">
                    <img
                        src={event.imageUrl} alt={event.title}
                        className="max-w-sm rounded-lg shadow-2xl" />
                    <div className="flex flex-col justify-start gap-2">
                        <h1 className="text-5xl font-bold">{event.title}</h1>
                        {locationNameP}
                        {locationAddressP}
                        {locationUrlP}
                        <p className="event-location">Start Date: {startDateFormatted}</p>
                        <p className="event-location">End Date: {endDateFormatted}</p>
                        <p className="py-6">
                            {event.description}
                        </p>
                        <a href={event.originalLink} target="_blank" rel="noopener noreferrer"
                           className="btn btn-primary" > Register</a>
                    </div>
                </div>
            </div>
        </div>
    );
}

export default EventDetail;
