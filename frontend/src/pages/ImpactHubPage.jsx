import { Award, Coins, Sparkles, Trophy, Zap } from 'lucide-react';

const challenges = [
  { title: 'Reduce commuting footprint', status: 'Available', xp: '+200 XP' },
  { title: 'Volunteer day participation', status: 'In progress', xp: '+150 XP' },
  { title: 'Policy compliance sprint', status: 'Pending approval', xp: '+300 XP' },
];

export default function ImpactHubPage() {
  return (
    <div className="container">
      <div className="page-card" style={{ marginBottom: 20 }}>
        <div className="badge" style={{ marginBottom: 10 }}><Sparkles size={14} /> Impact Hub</div>
        <h1 style={{ margin: '0 0 8px' }}>Challenges, XP, badges, rewards, and leaderboards</h1>
        <p className="muted">Guide employees through a complete challenge lifecycle with clear feedback and meaningful reward progression.</p>
      </div>

      <div className="grid grid-3" style={{ marginBottom: 20 }}>
        <div className="page-card">
          <div className="row-between"><strong>Current XP</strong><Zap size={18} /></div>
          <p style={{ fontSize: '1.5rem', fontWeight: 700, margin: '8px 0 0' }}>3,420</p>
        </div>
        <div className="page-card">
          <div className="row-between"><strong>Unlocked badges</strong><Award size={18} /></div>
          <p style={{ fontSize: '1.5rem', fontWeight: 700, margin: '8px 0 0' }}>7</p>
        </div>
        <div className="page-card">
          <div className="row-between"><strong>Available rewards</strong><Coins size={18} /></div>
          <p style={{ fontSize: '1.5rem', fontWeight: 700, margin: '8px 0 0' }}>4</p>
        </div>
      </div>

      <div className="grid grid-2">
        <div className="page-card">
          <div className="row-between" style={{ marginBottom: 14 }}>
            <strong>Challenge flow</strong>
            <span className="badge">Lifecycle</span>
          </div>
          <div className="list">
            {challenges.map((challenge) => (
              <div key={challenge.title} className="list-item">
                <div className="row-between">
                  <div>
                    <strong>{challenge.title}</strong>
                    <p className="muted" style={{ margin: '4px 0 0' }}>{challenge.status}</p>
                  </div>
                  <span className="badge">{challenge.xp}</span>
                </div>
              </div>
            ))}
          </div>
        </div>

        <div className="page-card">
          <div className="row-between" style={{ marginBottom: 14 }}>
            <strong>Leaderboard</strong>
            <Trophy size={18} />
          </div>
          <div className="list">
            <div className="list-item"><div className="row-between"><span>#1 Maya Chen</span><strong>9,200 XP</strong></div></div>
            <div className="list-item"><div className="row-between"><span>#2 Daniel Ortiz</span><strong>8,950 XP</strong></div></div>
            <div className="list-item"><div className="row-between"><span>#3 Aisha Khan</span><strong>8,620 XP</strong></div></div>
          </div>
        </div>
      </div>
    </div>
  );
}
