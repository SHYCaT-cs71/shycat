import React from 'react';

const Navbar = () => {
  return (
    <div className="navbar bg-white shadow-lg px-4 py-3" role="navigation">
      <div className="flex-1 flex items-center justify-start">
        <a className="text-2xl font-bold text-gray-800 hover:text-blue-600 transition-colors" data-testid="logo">
          SHYC&T
        </a>
      </div>

      {/* TODO: Implement search bar */}
      {/* <div className="flex-1 flex justify-center">
        <div className="form-control w-full max-w-md">
          <input
            type="text"
            data-testid="search"
            placeholder="Search"
            className="input input-bordered w-full border-gray-300 bg-gray-100 text-gray-700 placeholder-gray-500 focus:bg-gray-200 focus:border-blue-600"
          />
        </div>
      </div> */}

      {/* TODO: Implement user page */}
      {/* <div className="flex-1 flex items-center justify-end gap-4">
        <div className="dropdown dropdown-end">
          <div tabIndex={0} role="button" className="btn btn-ghost btn-circle avatar">
            <div className="w-10 rounded-full">
              <img
                alt="User Avatar"
                src="https://img.daisyui.com/images/stock/photo-1534528741775-53994a69daeb.webp"
              />
            </div>
          </div>
          <ul
            tabIndex={0}
            className="menu menu-sm dropdown-content bg-white border border-gray-200 rounded-lg mt-3 w-48 p-2 shadow-md z-[1]"
            data-testid="profile-dropdown"
          >
            <li>
              <a className="flex justify-between text-gray-700 hover:text-blue-600">
                Profile
                <span className="badge badge-primary text-xs">New</span>
              </a>
            </li>
            <li>
              <a className="text-gray-700 hover:text-blue-600">Settings</a>
            </li>
            <li>
              <a className="text-gray-700 hover:text-blue-600">Logout</a>
            </li>
          </ul>
        </div>
      </div> */}
    </div>
  );
};

export default Navbar;
