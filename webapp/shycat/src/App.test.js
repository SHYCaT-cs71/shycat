import { render, screen } from "@testing-library/react";
import App from "./App";
import { mockHarvardEvents } from "./data/Event";

afterAll(() => {
    fetch.resetMocks()
});

fetch.mockResponse(JSON.stringify(mockHarvardEvents));

test('renders navbar', async () => {
  render(<App />);
  const navbarElement = await screen.findByRole("navigation");
  expect(navbarElement).toBeInTheDocument();
});

test('renders EventList with events', async () => {
  render(<App />);
  const eventCards = await screen.findAllByTestId('event-card');
  expect(eventCards.length).toBe(mockHarvardEvents.length);
});

test('EventList renders events with correct titles', async () => {
  render(<App />);

  // First find the title for the events to make sure the page has loaded
  const app = await screen.findByText("Events from Backend");
  expect(app).toBeInTheDocument();

  // Then check that each event is present
  mockHarvardEvents.forEach((event) => {
    const eventTitle = screen.getByText(event.title);
    expect(eventTitle).toBeInTheDocument();
  });
});
