import { render, screen } from '@testing-library/react';
import EventList from './EventList';
import { getEventSummary } from "./EventCard";
import { mockHarvardEvents } from '../data/Event';

test('renders EventList with the correct number of EventCards', () => {
    render(<EventList events={mockHarvardEvents} />);

    const eventCards = screen.getAllByTestId('event-card');
    expect(eventCards.length).toBe(mockHarvardEvents.length);
});

test('renders all events with correct titles', () => {
    render(<EventList events={mockHarvardEvents} />);

    mockHarvardEvents.forEach((event) => {
        const title = screen.getByText(event.title);
        expect(title).toBeInTheDocument();
    });
});

test('renders all events with correct summaries', () => {
    render(<EventList events={mockHarvardEvents} />);

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
    render(<EventList events={mockHarvardEvents} />);

    mockHarvardEvents.forEach((event) => {
        const locationName = screen.getByText('Location: ' + event.locationName);
        expect(locationName).toBeInTheDocument();
    });
});

test('renders all events with correct start dates', () => {
    render(<EventList events={mockHarvardEvents} />);

    mockHarvardEvents.forEach((event) => {
        const startDate = screen.getByText('Start: ' + event.startDate);
        expect(startDate).toBeInTheDocument();
    });
});

test('renders all events with correct end dates', () => {
    render(<EventList events={mockHarvardEvents} />);

    mockHarvardEvents.forEach((event) => {
        if (event.endDate) {
            const startDate = screen.getByText('End: ' + event.endDate);
            expect(startDate).toBeInTheDocument();
        }
    });
});

test('renders all events with correct tags', () => {
    render(<EventList events={mockHarvardEvents} />);

    mockHarvardEvents.forEach((event) => {
      event.tags.forEach((tag) => {
        const tagElements = screen.getAllByText(tag);
        expect(tagElements.length).toBeGreaterThan(0);
      });
    });
});
