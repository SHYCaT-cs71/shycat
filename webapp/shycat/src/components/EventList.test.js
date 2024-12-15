import { render, screen } from '@testing-library/react';
import EventList from './EventList';
import { getEventSummary } from "./EventCard";
import { mockHarvardEvents } from '../data/Event';
import { formatDate } from '../data/Utilities'; // Import formatDate to match EventCard's logic
import { MemoryRouter } from "react-router-dom";


test('renders EventList with the correct number of EventCards', () => {
    render(<MemoryRouter><EventList events={mockHarvardEvents} /></MemoryRouter>);

    const eventCards = screen.getAllByTestId('event-card');
    expect(eventCards.length).toBe(mockHarvardEvents.length);
});

test('renders all events with correct titles', () => {
    render(<MemoryRouter><EventList events={mockHarvardEvents} /></MemoryRouter>);

    mockHarvardEvents.forEach((event) => {
        const title = screen.getByText(event.title);
        expect(title).toBeInTheDocument();
    });
});

test('renders all events with correct summaries', () => {
    render(<MemoryRouter><EventList events={mockHarvardEvents} /></MemoryRouter>);

    mockHarvardEvents.forEach((event) => {
        let summary;
        if (event.summary) {
            summary = screen.getByText(event.summary);
        }
        else {
            summary = screen.getByText(getEventSummary(event.description));
        }
        expect(summary).toBeInTheDocument();
    });
});

test('renders all events with correct locations', () => {
    render(<MemoryRouter><EventList events={mockHarvardEvents} /></MemoryRouter>);

    mockHarvardEvents.forEach((event) => {
        const locationName = screen.getByText('Location: ' + event.locationName);
        expect(locationName).toBeInTheDocument();
    });
});

test('renders all events with correct start dates', () => {
    render(<MemoryRouter><EventList events={mockHarvardEvents} /></MemoryRouter>);

    mockHarvardEvents.forEach((event) => {
        const formattedStartDate = `Start: ${formatDate(event.startDate)}`; // Use formatDate
        const startDate = screen.getByText(formattedStartDate);
        expect(startDate).toBeInTheDocument();
    });
});

test('renders all events with correct end dates', () => {
    render(<MemoryRouter><EventList events={mockHarvardEvents} /></MemoryRouter>);

    mockHarvardEvents.forEach((event) => {
        if (event.endDate) {
            const formattedEndDate = `End: ${formatDate(event.endDate)}`; // Use formatDate
            const endDate = screen.getByText(formattedEndDate);
            expect(endDate).toBeInTheDocument();
        }
    });
});

test('renders all events with correct tags', () => {
    render(<MemoryRouter><EventList events={mockHarvardEvents} /></MemoryRouter>);

    mockHarvardEvents.forEach((event) => {
      event.tags.forEach((tag) => {
        const tagElements = screen.getAllByText(tag);
        expect(tagElements.length).toBeGreaterThan(0);
      });
    });
});
