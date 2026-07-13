import { BriefcaseBusiness, Sparkles } from 'lucide-react';

const items = [
  { title: 'Departments', description: 'Organize teams and reporting units.' },
  { title: 'Categories', description: 'Manage ESG categories and structures.' },
  { title: 'Resources', description: 'Maintain resources and emission factors.' },
  { title: 'Configuration', description: 'Tune platform defaults and notifications.' },
];

export default function SettingsPage() {
  return (
    <div className="container">
      <div className="page-card" style={{ marginBottom: 20 }}>
        <div className="badge" style={{ marginBottom: 10 }}><BriefcaseBusiness size={14} /> Settings & Administration</div>
        <h1 style={{ margin: '0 0 8px' }}>Administration that feels calm and complete</h1>
        <p className="muted">Manage departments, categories, resources, configurations, and notification preferences in one place.</p>
      </div>

      <div className="grid grid-2">
        {items.map((item) => (
          <div key={item.title} className="page-card">
            <div className="badge"><Sparkles size={14} /> Admin</div>
            <h3>{item.title}</h3>
            <p className="muted">{item.description}</p>
          </div>
        ))}
      </div>
    </div>
  );
}
