import { render, screen } from '@testing-library/react';
import App from './App';
import { mockHarvardEvents } from './data/Event';

test('renders navbar', () => {
  render(<App />);
  const navbarElement = screen.getAllByRole("navigation");
  expect(navbarElement[0]).toBeInTheDocument();
});
