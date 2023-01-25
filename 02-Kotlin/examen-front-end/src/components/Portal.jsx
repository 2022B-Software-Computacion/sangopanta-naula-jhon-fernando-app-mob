import React, { useState } from 'react';
import '../styles/Modal.css';
import { ReactDOM } from 'react';

const portalRoot = document.getElementById('portal');

const Portal = ({ children }) => {
    const [el] = useState(document.createElement('div'));

    useEffect(() => {
        portalRoot.appendChild(el);

        return () => portalRoot.removeChild(el);
    }, [el]);

    return ReactDOM.createPortal(children, el);
}

export default Portal;