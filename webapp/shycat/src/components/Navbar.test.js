import { render, screen } from '@testing-library/react';
import Navbar from './Navbar';

test('logo exists', () => {
  render(<Navbar />);

  // Make sure the logo button is in the component
  const navbarLogoButton = screen.getByTestId("logo");
  expect(navbarLogoButton).toBeInTheDocument();

  const navbarLogoText = screen.getByText("SHYC&T");
  expect(navbarLogoText).toBeInTheDocument();

});

test('search bar', ()=>{
    render(<Navbar />);

    const searchBar = screen.getByPlaceholderText("Search");
    expect(searchBar).toBeInTheDocument();

    const searchBarById = screen.getByTestId("search");
    expect(searchBarById).toBeInTheDocument();

});

test('user profile button exists', ()=>{
  render(<Navbar />);

  const profileButton = screen.getAllByRole('button');
  expect(profileButton.length).toBe(1);
  expect(profileButton[0]).toBeInTheDocument();

});

test('user profile dropdown exists', ()=>{
    render(<Navbar />);
  
    const dropdownMenu = screen.getByTestId("profile-dropdown");
    expect(dropdownMenu).toBeInTheDocument();
    expect(dropdownMenu.childElementCount).toBe(3);

});
