import { render, screen } from '@testing-library/react';
import App from './App';
import { mockHarvardEvents } from './data/EventData';

test('renders navbar', () => {
  render(<App />);
  const navbarElement = screen.getAllByRole("navigation");
  expect(navbarElement[0]).toBeInTheDocument();
});

test('renders EventList with events', () => {
  render(<App />);
  const eventCards = screen.getAllByTestId('event-card');
  expect(eventCards.length).toBe(mockHarvardEvents.length);
});

test('EventList renders events with correct titles', () => {
  render(<App />);
  mockHarvardEvents.forEach((event) => {
    const eventTitle = screen.getByText(event.title);
    expect(eventTitle).toBeInTheDocument();
  });
});
