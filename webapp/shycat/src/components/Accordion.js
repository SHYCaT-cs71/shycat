import React from 'react';

const Accordion = () => {
  return (
    <div className="join join-vertical w-full">
      <div className="collapse collapse-arrow join-item border-base-300 border">
        <input type="radio" name="my-accordion" defaultChecked />
        <div className="collapse-title text-xl font-medium">Click to open this one and close others</div>
        <div className="collapse-content">
          <p>Hello</p>
        </div>
      </div>
      <div className="collapse collapse-arrow join-item border-base-300 border">
        <input type="radio" name="my-accordion" />
        <div className="collapse-title text-xl font-medium">Click to open this one and close others</div>
        <div className="collapse-content">
          <p>Hello</p>
        </div>
      </div>
      <div className="collapse collapse-arrow join-item border-base-300 border">
        <input type="radio" name="my-accordion" />
        <div className="collapse-title text-xl font-medium">Click to open this one and close others</div>
        <div className="collapse-content">
          <p>Hello</p>
        </div>
      </div>
    </div>
  );
};

export default Accordion;
