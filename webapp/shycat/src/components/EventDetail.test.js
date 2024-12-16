import { render, screen } from '@testing-library/react';
import { mockHarvardEvents } from '../data/Event';
import EventDetail from './EventDetail';

afterAll(() => {
  fetch.resetMocks()
});

fetch.mockResponse(JSON.stringify(mockHarvardEvents[0]));

test('title exists', async () => {
  render(<EventDetail />);
  
  const title = await screen.findByText(mockHarvardEvents[0].title);
  expect(title).toBeInTheDocument();
});

test('description exists', async () => {
  render(<EventDetail />);
  
  const summary = await screen.findByText(mockHarvardEvents[0].description);
  expect(summary).toBeInTheDocument();
});

test('button exists', async () => {
  render(<EventDetail />);
  
  const button = await screen.findByText("Register");
  expect(button).toBeInTheDocument();
});

test('event link present for virtual location', async () => {
    // Return virtual event
    fetch.mockResponseOnce(JSON.stringify(mockHarvardEvents[4]));

    render(<EventDetail />);

    const eventLink = await screen.findByText("Event Link: https://example.com/virtual-zoom-event");
    expect(eventLink).toBeInTheDocument();
});

test('event link not present for place', async () => {
    render(<EventDetail />);

    // Get the title to wait for it to render
    await screen.findByText(mockHarvardEvents[0].title);

    const eventLink = screen.queryByTestId("locationUrl");
    expect(eventLink).not.toBeInTheDocument();
});
