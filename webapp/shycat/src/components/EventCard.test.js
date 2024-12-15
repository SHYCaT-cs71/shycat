import { render, screen } from '@testing-library/react';
import EventCard, { getEventSummary } from './EventCard';
import { mockHarvardEvents } from '../data/Event';
import {MemoryRouter} from "react-router-dom";
import { formatDate } from "../data/Utilities";
// Match component logic for formatting dates



const mockHarvardEvent = mockHarvardEvents[0];

test('image exists', () => {
  render(<MemoryRouter><EventCard event={mockHarvardEvent} /></MemoryRouter>);
  
  const image = screen.getAllByRole("img");
  expect(image.length).toBe(1);
  expect(image[0]).toBeInTheDocument();
  expect(image[0]).toHaveAttribute('src', mockHarvardEvent.imageUrl);
});

test('title exists', () => {
  render(<MemoryRouter><EventCard event={mockHarvardEvent} /></MemoryRouter>);
  
  const title = screen.getByText(mockHarvardEvent.title);
  expect(title).toBeInTheDocument();
});

test('summary exists', () => {
  render(<MemoryRouter><EventCard event={mockHarvardEvent} /></MemoryRouter>);
  
  const summary = screen.getByText(mockHarvardEvent.summary);
  expect(summary).toBeInTheDocument();
});

test('tags exist', () => {
  render(<MemoryRouter><EventCard event={mockHarvardEvent} /></MemoryRouter>);
  
  mockHarvardEvent.tags.forEach((tag) => {
    expect(screen.getByText(tag)).toBeInTheDocument();
  });
});

test('location exists', () => {
  render(<MemoryRouter><EventCard event={mockHarvardEvent} /></MemoryRouter>);
  
  const location = screen.getByText('Location: ' + mockHarvardEvent.locationName);
  expect(location).toBeInTheDocument();
});

test('start date exists', () => {
  render(<MemoryRouter><EventCard event={mockHarvardEvent} /></MemoryRouter>);
  
  const formattedStartDate = `Start: ${formatDate(mockHarvardEvent.startDate)}`;
  const startDate = screen.getByText(formattedStartDate);
  expect(startDate).toBeInTheDocument();
});


test('end date exists', () => {
  render(<MemoryRouter><EventCard event={mockHarvardEvent} /></MemoryRouter>);
  
  const formattedEndDate = `End: ${formatDate(mockHarvardEvent.endDate)}`;
  const endDate = screen.getByText(formattedEndDate);
  expect(endDate).toBeInTheDocument();
});

test('get truncated description', () => {

  const description = "This annual event brings together the Harvard community to witness and support the entrepreneurial ideas of Harvard's brightest minds. Innovators pitch their projects to potential investors, gaining invaluable feedback and visibility. From tech startups to social enterprises, attendees will see a diverse array of ventures aimed at addressing real-world issues. It’s also a great networking opportunity for anyone interested in entrepreneurship and innovation.";

  const summary = getEventSummary(description);

  expect(summary).toBe(
    "This annual event brings together the Harvard community to witness and support the entrepreneurial ideas of Harvard's brightest minds."
  )
});


test("falls back to default image if imageUrl is invalid", () => {
  const invalidUrl = null; // Simulate an invalid or missing URL
  const fallbackUrl = "/shycatfallback.jpg";

  render(
      <MemoryRouter>
        <EventCard
          event={{
            imageUrl: invalidUrl,
            title: "Test Event",
            summary: "summary",
            description: "description",
            tags: [],
            startDate: "2024-12-04",
          }}
        />
      </MemoryRouter>
  );

  const image = screen.getByRole("img");

  // Assert that the image src is set to the fallback URL
  expect(image).toHaveAttribute("src", fallbackUrl);
});
