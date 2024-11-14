import { render, screen } from '@testing-library/react';
import EventList from './EventList';
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
        const summary = screen.getByText(event.summary);
        expect(summary).toBeInTheDocument();
    });
});

test('renders all events with correct locations', () => {
    render(<EventList events={mockHarvardEvents} />);

    mockHarvardEvents.forEach((event) => {
        const location = screen.getByText(event.location);
        expect(location).toBeInTheDocument();
    });
});

test('renders all events with correct dates', () => {
    render(<EventList events={mockHarvardEvents} />);

    mockHarvardEvents.forEach((event) => {
        const formattedDate = event.date.toLocaleString();
        const date = screen.getByText(formattedDate);
        expect(date).toBeInTheDocument();
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
