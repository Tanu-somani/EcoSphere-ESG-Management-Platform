import { Leaf, Search, Sparkles } from 'lucide-react';

const items = [
  { name: 'Energy Consumption', category: 'Operations', value: '2.1M kWh' },
  { name: 'Carbon Emissions', category: 'Scope 1', value: '184 tCO2e' },
  { name: 'Recycling Rate', category: 'Waste', value: '78%' },
];

export default function EnvironmentalPage() {
  return (
    <div className="container">
      <div className="page-card" style={{ marginBottom: 20 }}>
        <div className="badge" style={{ marginBottom: 10 }}><Leaf size={14} /> Environmental Intelligence</div>
        <h1 style={{ margin: '0 0 8px' }}>Environmental performance at a glance</h1>
        <p className="muted">Track emissions, sustainability goals, and department-level carbon performance from one responsive workspace.</p>
        <div className="row-between" style={{ marginTop: 14 }}>
          <div style={{ position: 'relative', width: 'min(320px, 100%)' }}>
            <Search size={16} style={{ position: 'absolute', left: 12, top: 12, color: '#94a3b8' }} />
            <input className="input" style={{ paddingLeft: 36 }} placeholder="Search environmental metrics" />
          </div>
          <button className="btn btn-primary">+ New report</button>
        </div>
      </div>

      <div className="grid grid-3">
        {items.map((item) => (
          <div key={item.name} className="page-card">
            <div className="badge"><Sparkles size={14} /> {item.category}</div>
            <h3 style={{ marginBottom: 6 }}>{item.name}</h3>
            <p style={{ fontSize: '1.35rem', fontWeight: 700, margin: 0 }}>{item.value}</p>
          </div>
        ))}
      </div>
    </div>
  );
}
