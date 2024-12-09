import React from 'react';
import { utcToZonedTime } from 'date-fns-tz';
import { format, parseISO, parse, isValid } from 'date-fns';

export const getEventSummary = (description) => {
    return description.split(". ")[0] + ".";
};

const EventCard = ({ event }) => {

    const startDateFormatted = event.startDate ? formatDate(event.startDate) : "No start date";
    const endDateFormatted = event.endDate ? formatDate(event.endDate) : null;

    let endDateP;
    if (endDateFormatted) {
        endDateP = <p className="event-date">End: {endDateFormatted}</p>;
    }

    let locationNameP;
    if (event.locationName) {
        locationNameP = <p className="event-location">Location: {event.locationName}</p>;
    }

    let locationAddressP;
    if (event.locationAddress) {
        locationAddressP = <p className="event-location">Address: {event.locationAddress}</p>;
    }

    let summaryP;
    if (event.summary) {
        summaryP = <p className="event-summary">{event.summary}</p>;
    } else {
        summaryP = <p className="event-summary">{getEventSummary(event.description)}</p>;
    }

    return (
        <div data-testid="event-card" className="card card-compact bg-base-100 w-96 shadow-xl">
            <figure>
                <img
                    src={event.imageUrl || "/shycatfallback.jpg"}
                    alt={event.title || "Default Event Title"}
                />
            </figure>
            <div className="card-body">
                <h2 className="card-title">{event.title}</h2>
                <div className="event-info">
                    {locationNameP}
                    {locationAddressP}
                    <p className="event-date">Start: {startDateFormatted}</p>
                    {endDateP}
                </div>
                {summaryP}
                <div className="card-tags">
                    {event.tags.map((tag, index) => (
                        <div className="badge badge-outline" key={index}>{tag}</div>
                    ))}
                </div>
            </div>
        </div>
    );
};

export default EventCard;

export const formatDate = (dateString) => {
    try {
        const parsedDate = dateString.includes('T')
            ? parseISO(dateString)
            : parse(dateString, 'yyyy-MM-dd', new Date());
        const formatString = dateString.includes('T') ? 'MMMM do, yyyy h:mm a (zzz)' : 'MMMM do, yyyy';
        return format(parsedDate, formatString);
    } catch {
        return 'Invalid Date';
    }
};
