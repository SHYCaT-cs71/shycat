import { render, screen } from '@testing-library/react';
import EventCard, { getEventSummary } from './EventCard';
import { mockHarvardEvents } from '../data/Event';

const mockHarvardEvent = mockHarvardEvents[0];

test('image exists', () => {
  render(<EventCard event={mockHarvardEvent} />);
  
  const image = screen.getAllByRole("img");
  expect(image.length).toBe(1);
  expect(image[0]).toBeInTheDocument();
  expect(image[0]).toHaveAttribute('src', mockHarvardEvent.imageUrl);
});

test('title exists', () => {
  render(<EventCard event={mockHarvardEvent} />);
  
  const title = screen.getByText(mockHarvardEvent.title);
  expect(title).toBeInTheDocument();
});

test('summary exists', () => {
  render(<EventCard event={mockHarvardEvent} />);
  
  const summary = screen.getByText(mockHarvardEvent.summary);
  expect(summary).toBeInTheDocument();
});

test('tags exist', () => {
  render(<EventCard event={mockHarvardEvent} />);
  
  mockHarvardEvent.tags.forEach((tag) => {
    expect(screen.getByText(tag)).toBeInTheDocument();
  });
});

test('location exists', () => {
  render(<EventCard event={mockHarvardEvent} />);
  
  const location = screen.getByText('Location: ' + mockHarvardEvent.locationName);
  expect(location).toBeInTheDocument();
});

test('date exists', () => {
  render(<EventCard event={mockHarvardEvent} />);

  const date = screen.getByText('Start: ' + mockHarvardEvent.startDate);
  expect(date).toBeInTheDocument();
});

test('get truncated description', () => {

  const description = "This annual event brings together the Harvard community to witness and support the entrepreneurial ideas of Harvard's brightest minds. Innovators pitch their projects to potential investors, gaining invaluable feedback and visibility. From tech startups to social enterprises, attendees will see a diverse array of ventures aimed at addressing real-world issues. It’s also a great networking opportunity for anyone interested in entrepreneurship and innovation.";

  const summary = getEventSummary(description);

  expect(summary).toBe(
    "This annual event brings together the Harvard community to witness and support the entrepreneurial ideas of Harvard's brightest minds."
  )
});
