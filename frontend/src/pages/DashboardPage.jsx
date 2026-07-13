import { Activity, BadgeCheck, Leaf, ShieldCheck, Sparkles, Users } from 'lucide-react';

const cards = [
  { title: 'Overall ESG Score', value: '87.4', hint: 'Updated 2h ago', tone: 'metric-card' },
  { title: 'Environmental Performance', value: '91%', hint: 'Carbon initiatives healthy', tone: 'metric-card violet' },
  { title: 'Social Performance', value: '76%', hint: 'Engagement strong', tone: 'metric-card' },
  { title: 'Governance Readiness', value: '89%', hint: 'Compliance aligned', tone: 'metric-card navy' },
];

export default function DashboardPage() {
  return (
    <div className="container">
      <div className="row-between" style={{ marginBottom: 16 }}>
        <div>
          <div className="badge" style={{ marginBottom: 8 }}><Sparkles size={14} /> EcoSphere Intelligence</div>
          <h1 style={{ margin: 0 }}>Enterprise ESG command center</h1>
        </div>
      </div>

      <div className="grid grid-2" style={{ marginBottom: 20 }}>
        {cards.map((card) => (
          <div key={card.title} className={card.tone}>
            <p className="muted" style={{ margin: 0 }}>{card.title}</p>
            <div style={{ fontSize: '1.8rem', fontWeight: 700, marginTop: 6 }}>{card.value}</div>
            <p className="muted" style={{ marginTop: 6 }}>{card.hint}</p>
          </div>
        ))}
      </div>

      <div className="grid grid-2">
        <div className="page-card">
          <div className="row-between" style={{ marginBottom: 14 }}>
            <strong>Impact Pulse</strong>
            <span className="badge">Connected</span>
          </div>
          <div className="list">
            <div className="list-item"><div className="row-between"><span><Leaf size={16} /> Emissions</span><strong>↓ 14%</strong></div></div>
            <div className="list-item"><div className="row-between"><span><Users size={16} /> Employee participation</span><strong>82%</strong></div></div>
            <div className="list-item"><div className="row-between"><span><ShieldCheck size={16} /> Policy adherence</span><strong>96%</strong></div></div>
          </div>
        </div>

        <div className="page-card">
          <div className="row-between" style={{ marginBottom: 14 }}>
            <strong>Recent activity</strong>
            <span className="badge">Live feed</span>
          </div>
          <div className="list">
            <div className="list-item"><Activity size={16} /> Carbon reduction target updated.</div>
            <div className="list-item"><BadgeCheck size={16} /> Compliance audit submitted.</div>
            <div className="list-item"><Sparkles size={16} /> New challenge available in Impact Hub.</div>
          </div>
        </div>
      </div>
    </div>
  );
}
