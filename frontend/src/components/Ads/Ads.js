import React from 'react'

const Ads = ({ ads }) => {

    let renderAd= (ad) => {
        const { name, description } = ad;
        return (
            <li>
                <div className="name">{name}</div>
                <div className="description">{description}</div>
            </li>
        );
    };

    return (
        <ul className="list">
            {ads.map(msg => renderAd(msg))}
        </ul>
    )
}

export default Ads
