package com.example.objetos;

import android.R;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.proyecto.CreateTagContent;
import com.example.proyecto.CustomDialog;
import com.example.proyecto.TagUIContent;

public class CustomAdapater extends ArrayAdapter<TagUIContent> {

	private final Activity context;
	private TagUIContent[] objects;

	public CustomAdapater(Activity context,  TagUIContent[] objects) {
		super(context, com.example.proyecto.R.layout.recent_content, objects);
		// TODO Auto-generated constructor stub
		this.context = context;
		this.objects = objects;
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		 View rowView = convertView;
		 /*
		 final TextView payload = (TextView)convertView.findViewById(com.example.proyecto.R.id.contentPayload);
		 final TextView payloadDesc = (TextView)convertView.findViewById(com.example.proyecto.R.id.contentDescription);
		 final TextView id = (TextView)convertView.findViewById(com.example.proyecto.R.id.contentId);
		 */
		 if(rowView == null){
			 LayoutInflater inflater = context.getLayoutInflater();
			 rowView = inflater.inflate(com.example.proyecto.R.layout.recent_content,null);
			 
			 ViewHolder viewHolder = new ViewHolder();
			 viewHolder.payloadContent =  (TextView) rowView.findViewById(com.example.proyecto.R.id.contentPayload);
			 viewHolder.payloadDescContent = (TextView) rowView.findViewById(com.example.proyecto.R.id.contentDescription);
			 viewHolder.payloadContentId  = (TextView) rowView.findViewById(com.example.proyecto.R.id.contentId);
			 viewHolder.payloadIconContent = (ImageView) rowView.findViewById(com.example.proyecto.R.id.contentIcon);
			 rowView.setTag(viewHolder);
		 }
		 
		 
		 final ViewHolder holder = (ViewHolder)rowView.getTag();
		 
		 //LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);		 
		 
		 Log.d("debug extra",objects[position].getContentDesc().getText().toString());
		 holder.payloadIconContent.setBackground(objects[position].getContentIcon().getBackground());
		 holder.payloadContent.setText(objects[position].getPayload().getText());
		 holder.payloadDescContent.setText(objects[position].getContentDesc().getText());
		 holder.payloadContentId.setText(objects[position].getContentId().getText());
		 
		 rowView.setOnClickListener(new View.OnClickListener() {
				
			 
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
				
					Intent intent = new Intent(getContext(), CreateTagContent.class);
					String itemId = holder.payloadContentId.getText().toString();
				    String kind = holder.payloadDescContent.getText().toString();
				    String pLoad = holder.payloadContent.getText().toString();
				    Log.d("debug extra",kind);
					intent.putExtra("CONTENT_KIND", kind);
					intent.putExtra("CONTENT_PAYLOAD", pLoad);
					intent.putExtra("CONTENT_ID", itemId);
					
					getContext().startActivity(intent);
				}
				
			});
		 
		 rowView.setOnLongClickListener(new View.OnLongClickListener() {
				

				
				Long itemId = Long.valueOf(holder.payloadContentId.getText().toString());
				private ListView optionDialog;
				private CustomDialog dialog;
				private TagContentDataSource datasource;
				
				//datasource = new TagContentDataSource(getContext());
			    //datasource.open();

				@Override
				public boolean onLongClick(View arg0) {
					// TODO Auto-generated method stub
					
					optionDialog = new ListView(getContext());
					String[] cOptionsArrayStrings =  getContext().getResources().getStringArray(com.example.proyecto.R.array.cOptions_array);
					
					ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1, cOptionsArrayStrings);
					
					optionDialog.setAdapter(adapter);
					optionDialog.setOnItemClickListener(new AdapterView.OnItemClickListener() {

						@Override
			            public void onItemClick(AdapterView<?> parent, final View view,
			                int position, long id) {
			              final String item = (String) parent.getItemAtPosition(position);
			              
			              
			              switch (position) {
						case 0:
							Toast.makeText(getContext(), "Delete!", Toast.LENGTH_LONG).show();
							datasource.deleteComment(itemId);
							
							break;
						
						default:
							break;
						}
			              
			              dialog.dismiss();
			            }

			          });
					
					dialog = new CustomDialog(getContext());
					//dialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
					dialog.setTitle("Options");
					dialog.setContentView(optionDialog);
					
					dialog.show();
					
					
					
					return true;
				}
			});
		 
		return rowView;
	}
	
	static class ViewHolder{
		public TextView payloadContent;
		public TextView payloadDescContent;
		public ImageView payloadIconContent;
		public TextView payloadContentId;
	}
}
