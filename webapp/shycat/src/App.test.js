import { render, screen } from '@testing-library/react';
import App from './App';

test('renders welcome message', () => {
  render(<App />);
  const welcomeElement = screen.getByText(/welcome to shycat/i);
  expect(welcomeElement).toBeInTheDocument();
});


test('renders navbar', () => {
  render(<App />);

  const navbarElement = screen.getAllByRole("navigation");
  expect(navbarElement[0]).toBeInTheDocument();
});
