import { render, screen } from '@testing-library/react';
import Accordion from './Accordion';

test('renders accordion component', () => {
  render(<Accordion />);
  const accordionItems = screen.getAllByText(/Click to open this one and close others/i);
  expect(accordionItems.length).toBe(3);
});
