# -*- coding: utf-8 -*-
{
    'name': 'EcoSphere - ESG AI Copilot',
    'summary': 'AI Copilot for ESG analysis, summaries, and recommendations in EcoSphere.',
    'description': """
EcoSphere ESG AI Copilot
========================
Provides intelligence and recommendations on:
- ESG Metrics
- Carbon Emissions
- Department Performance
- Goals and Audits
- CSR Activities
    """,
    'author': 'EcoSphere AI Solutions Architect',
    'category': 'Sustainability/AI',
    'version': '1.0',
    'depends': ['base', 'web'],
    'data': [
        'security/ir.model.access.csv',
        'data/ir_config_parameter_data.xml',
    ],
    'assets': {
        'web.assets_backend': [
            'eco_sphere_ai/static/src/css/ai_chat.css',
            'eco_sphere_ai/static/src/js/ai_chat.js',
        ],
    },
    'installable': True,
    'application': True,
    'auto_install': False,
    'license': 'LGPL-3',
}
