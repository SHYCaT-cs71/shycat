import { render, screen } from '@testing-library/react';
import EventCard from './EventCard';
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
  
  const location = screen.getByText(mockHarvardEvent.location);
  expect(location).toBeInTheDocument();
});

test('date exists', () => {
  render(<EventCard event={mockHarvardEvent} />);
  
  // The date is formatted with toLocaleString(), so we check the string representation.
  const formattedDate = mockHarvardEvent.date.toLocaleString();
  const date = screen.getByText(formattedDate);
  expect(date).toBeInTheDocument();
});
